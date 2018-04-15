(ns atoms)

(def foo (atom {:blah "this"}))

(deref foo)
@foo

(swap! foo (fn [old] 3))
(swap! foo inc)

(pmap
  (fn [_] (swap! foo inc))
  (range 50))

;
(import [java.lang Thread])

(def bar (atom 0))
(def call-counter (atom 0))

(defn slow-inc-with-call-count [x]
  (swap! call-counter inc)
  (Thread/sleep 100)
  (inc x))

(pmap
  (fn [_]
    (swap! bar slow-inc-with-call-count))
  (range 50))

