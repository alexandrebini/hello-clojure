(ns agents)

(def my-agent
  (agent
    {:name "craig-andera"
     :favorites []}))

(defn slow-append-favorite
  [val new-fav]
  (Thread/sleep 2000)
  (assoc val
    :favorites
    (conj (:favorites val) new-fav)))

(do
  (send my-agent slow-append-favorite "food")
  (send my-agent slow-append-favorite "music")
  (println @my-agent)
  (Thread/sleep 2500)
  (println @my-agent)
  (Thread/sleep 2500)
  (println @my-agent))

(def erroring-agent (agent 3))

(defn modify-agent-with-error
  "Modifie an agent with error on 42"
  [current new]
  (if (= 42 new)
    (throw (Exception. "Not 42!"))
    new))

(send erroring-agent
  modify-agent-with-error 42)

(send erroring-agent
  modify-agent-with-error 17)
