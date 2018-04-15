(ns refs)

(def foo
  (ref {:first "Craig" :last "Andera" :children 2}))

(assoc @foo :blog "http://batata.com")

; (commute foo
;   assoc :blog "http://batatacom")
; raise No transaction running

(dosync
  (commute foo
    assoc :blog "http://batata.com"))

(dosync
  (alter foo
    assoc :blog "http://batata2.com"))

;;;;;;;;;;;;;;;;;;;;; writes
(defmacro with-new-thread [& body]
  `(.start (Thread. (fn [] ~@body))))

(def w (ref 0))

(with-new-thread
  (dosync
    (println "tx1 initial:" @w)
    (alter w inc)
    (println "tx1 final:" @w)
    (Thread/sleep 5000)
    (println "tx1 done")))

(with-new-thread
  (dosync
    (println "tx2 initial:" @w)
    (Thread/sleep 1000)
    (alter w inc)
    (println "tx2 final:" @w)
    (println "tx2 done")))

; tx1 initial: 0
; tx1 final: 1
; tx2 initial: 0
; nil
; user=> tx2 initial: 0
; tx2 initial: 0
; tx2 initial: 0
; tx2 initial: 0
; tx1 done
; tx2 initial: 1
; tx2 final: 2
; tx2 done

;;;;;;;;;;;;;;;;;;;;; reads
(def r (ref 0))

; without transactions
(with-new-thread
  (dotimes [_ 10]
    (Thread/sleep 1000)
    (dosync (alter r inc))
    (println "updated ref to" @r)))

(with-new-thread
  (dotimes [_ 7]
    (println "ref is" @r)
    (Thread/sleep 1000)))

; ref is nil0
; updated ref to 1
; ref is 1
; updated ref to 2
; ref is 2
; updated ref to 3
; ref is 3
; updated ref to 4
; ref is 4
; ref is 4
; updated ref to 5
; ref is 5
; updated ref to 6
; updated ref to 7
; updated ref to 8
; updated ref to 9
; updated ref to 10

; with transactions
(dosync (ref-set r 0))

(with-new-thread
  (dotimes [_ 10]
    (Thread/sleep 1000)
    (dosync (alter r inc))
    (println "updated ref to" @r)))

(with-new-thread
  (println "r outside is" @r)
  (dosync
    (dotimes [i 7]
      (println "iter" i)
      (println "r is" @r)
      (Thread/sleep 1000)))
  (println "r outside is" @r))

; coordinated reads
(def r1 (ref 0))
(def r2 (ref 0))

(with-new-thread
  (dotimes [_ 10]
    (dosync
      (alter r1 inc)
      (Thread/sleep 500)
      (alter r2 inc))
    (println "updated ref1/2 to" @r1)))

(with-new-thread
  (dotimes [_ 10]
    (println "r1=" @r1 "r2=" @r2)
    (Thread/sleep 500)))
