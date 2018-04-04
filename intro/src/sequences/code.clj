(ns sequences)

(def a-range (range 1 4))

(def fibs
  (map first
    (iterate (fn [[a b]]
            [b (+ a b)])
          [0 1])))
