(require '[clojure.core.match :refer [match]])
(require '[defun.core :refer [defun]])
;; gcd - нод (наибольший общиий делитель)

(defn gcd [a b] (let [reminder (mod a b)] (if (= reminder 0) b (gcd b reminder))))
(gcd 20 16) ;; 4
(defn gcd' [a b] (let [reminder (mod a b)] (if (= reminder 0) b (recur b reminder))))

(defn num-to-str [n] 
  (match n
    1 "one"
    2 "two"
    :else "dark"))

(defn hello 
  ([] "Hello Guest")
  ([user] (str "Hello " user))
  ([user1 user2] (str "Hello " user1 " and " user2)))

(defun num-to-str' 
  ([1] "one")
  ([2] "two")
  ([n] "dark"))

(defun is-empty? ([[]] true) ([a-vector] false))
;; _ какое-то значение есть, нам не важно какое
(defun is-empty?' ([([] :seq)] true) ([_] false))

(defn is-empty-match [a-list]
  (match a-list
    ([] :seq) true
    :else false))

;; x - first, xs - rest
(defun my-first
  ([([] :seq)] nil)
  ([([x & xs] :seq)] x))

(defun my-rest
  ([([] :seq)] nil)
  ([([x] :seq)] "no rest")
  ([([x & xs] :seq)] xs))

;; factorial in huskell
;; fact 0 = 1
;; fact n = n *  fact (n-1)
(defun fact
  ([0] 1)
  ([n] (* n (fact (- n  1)))))

;; fibonacci
(defun fib
  ([0] 0)
  ([1] 1)
  ([(n :guard #(> % 0))] (+ (fib (- n 1)) (fib (- n 2)))))

(defun sum-of-seq 
  ([n](recur n 0))
  ([0 acc] acc)
  ([n acc] (recur (dec n) (+ n acc))))

(defn count-up [max] 
  (loop [count 0]
    (if (= count max) 
      (print "The end.") 
      (do (println (str "Step " count)) (recur (inc count))))))

(defn count-up' [count max] 
  (if (= count max)
    (print "The end.")
    (do (println (str "Step " count)) (recur (inc count) max))))

;; KT
(defun gcd''
  ([b 0] b)
  ([a b] (recur b (mod a b))))