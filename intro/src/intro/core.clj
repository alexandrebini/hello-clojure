(ns intro.core)

(defn messenger
  ([] (messenger "Hello world"))
  ([msg] (print msg))
  ([greeting & who] (print greeting who))
  )

; (defn messenger2 [greeting & who]
;   (print greeting who))

; (defn messenger3 [greeting & who]
;   apply print who)

; (let [numbers '(1 2 3)]
;   (apply + numbers))

; (messenger2 "hello" "world" "class")
; (messenger3 "hello" "world" "class")
