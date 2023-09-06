(defn palindrom? [word] (= (reverse word) (seq word)))
;;  функции для коллекций conj, cons, concat, count, reverse,  nth '() 1, .contains n, first, second, last, rest/next, take n, drop n, cycle, .indexOf, empty?, coll?, sequential?, associative?, counted?

;; (into [] '(1 2 3)) // [1 2 3]; (into '() [1 2 3]) // (3 2 1);

;; (range 5) // (0 1 2 3 4); (range 1 5) // (1 2 3 4); (range 1 10 2) // (1 3 5 7 9); (range 10 -1 -1) // (10 9 8 7 6 5 4 3 2 1 0); (range) - бесконечная последовательность

;; (repeatedly n) - типо цикл (можно  функции)

;; типо forEach
;; (doseq [x '(1 2 3)] (println x)) // 1 2 3 nil
;; (doseq [x1 '(1 2) x2 '(3 4)] (println (+ x1 x2))) // 4 5 5 6 nil

;; типо for
;; (for [x '(10 11 12)] (+ 1 x)) // (11 12 13)
;; (for '(1 2 -1) :when (< 0 x)] x) // (1 2)

;; const nord = 'nord'
;; :nord

;; hash-map {} = dictionary/object
;; {"name" "John" "age" 25}  or {:name "John" :age 25}
;; (get {:name "John" :age 25} :name) // john
;; (get {:name "John" :age 25} :admin) // nil
;; (get {:name "John" :age 25} :admin "nothing") // nothing
;; (:name {:name "John" :age 25}) // john
;; (assoc {:name "John" :age 25} :admin true) - to add a key-value
;; (assoc {:name "John" :age 25} :name "pete") - to change a key-value
;; (merge {:name "John" :age 25} {:admin true}) - to merge hash-maps
;; (keys {:name "John" :age 25}) - to take keys
;; (vals {:name "John" :age 25}) - to take values

(for [x [1 2 3 4 5] :let [y (* x 3)] :when (even? y)] y)
(for [x [1 2 3] y [4 5 6]] [x y])


(def long-seq (range))
(take 5 long-seq)
;;  vector - []
;; множества (set '()) или #{} // disj, conj, sort - в список, (contains? #{1 2 3} 6)
(defn zeros [n] (take n (cycle '(0))))


;; KT
(defn repeat' [n] (cycle [n]))
(take 5 (repeat' 3))

(defn subseq' [start end seq' ] (take (- end start) (drop start seq')))
(subseq' 2 5 (range 1 10)) ;; (3 4 5)

(defn in-first-half [n seq']
  (if (>= (.indexOf seq' n) 0)
   (let [half (/ (count seq') 2)]
     (if (< (.indexOf seq' n) half) true false)) "no such item"
  ))
(in-first-half 0 [1 2 3 4 5 6 7 8 9 10 11])

