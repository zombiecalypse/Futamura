(ns futamura.test.core
  (:use [futamura.core])
  (:use [clojure.test]))

(deftest curry-does-not-throw
  (is (curry
            (fn [x y] 
              (+ x y)) 1)))

(deftest curry-curries
  (is (= ((curry
            (fn [x y] 
              (+ x y)) 1) 2) 
         3)))

(deftest curry-curries-two
  (is (= ((curry
            (fn [x y] 
              (+ x y)) 1 2))
         3)))

(deftest curry-curries-itself
  (is (= (((curried +) 1 2 3 4)) 10)))

(deftest mcurry-expands-trivial
  (is
        (= 1 ((mcurry (fn [x] x)) 1)))
  (is 
        (= 1 ((mcurry (fn [x] x) 1)))))

(deftest spec-does-partial
  (is
    (= 3 (spec '+ 1) 2)))
