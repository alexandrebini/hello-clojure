(ns vars)

(def quux 17)

(def bar {:a 1 :b 2})

(def ^:dynamic foo {:name "Craig" :age 38.3})

(defn print-foo
  ([] (print-foo ""))
  ([prefix] (println prefix foo)))

(binding [foo 3]
  (print-foo "binding"))

(import [java.lang Thread])

(defn with-new-thread [f]
  (.start (Thread. f)))

(with-new-thread
  (fn [] (print-foo "new-thread:")))

(do
  (binding [foo "Keith"]
    (with-new-thread
      (fn []
        (binding [foo "Batata"]
          (print-foo "background:"))))
    (print-foo "foreground1:"))
  (print-foo "foreground2:"))

