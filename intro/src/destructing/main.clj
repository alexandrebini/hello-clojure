(ns destructing)

(def names ["Boby" "Sally" "Joe"])

(defn rest-names [[_ & rest-names]]
  rest-names)

(defn draw-point [& {:keys [x y z]
                     :or {x 0 y 0 z 0}}]
  [x y z])
