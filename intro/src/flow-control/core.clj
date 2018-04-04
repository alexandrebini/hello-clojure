(ns flowcontrol)

(defn show-evens [coll]
  (if-let [evens (seq (filter even? coll))]
    (println (str "The evens are: " evens))
    (println "there were no evens")))

(defn foo [x]
  (condp = x
    5 "x is 5"
    10 "x is 10"
    "x is not 5 or 10"))

(defn str-binary [n]
  (condp = n
    0 "zero"
    1 "one"
    "unkown"))

(defn factorial
  ([n] (factorial 1 n))
  ([accum n]
   (if (zero? n)
     accum
     (recur (*' accum n) (dec n)))))
