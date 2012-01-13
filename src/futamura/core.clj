(ns futamura.core)

(defn curry 
  "Curry is a non-optimizing specializer"
  ([f] {:pre [(fn? f)] :post [(fn? %)]} 
   f)
  ([f value] {:pre [(fn? f)] :post [(fn? %)]} 
   (fn [& more] (apply f value more)))
  ([f value & more] {:pre [(fn? f)] :post [(fn? %)]} 
   (apply curry (curry f value) more)))

(defmacro mcurry
  ([f] f)
  ([f value]
   `(fn [& more#] (apply ~f ~value more#)))
  ([f value & more]
   (apply mcurry (mcurry f value) more)))

(defn curried [f]
  {:pre [(fn? f)] :post [(fn? %)]}
  (mcurry curry f))

(defn spec [sym_f & x]
  "Returns specialized evaluation of sym_f"
  (apply partial (cons (eval sym_f) x)))

(defn compiler [interpreter]
  (spec `spec interpreter))
  
