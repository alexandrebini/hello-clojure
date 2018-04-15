(ns macros)

(defmacro with-new-thread [& body]
  `(.start (Thread. (fn [] ~@body))))
