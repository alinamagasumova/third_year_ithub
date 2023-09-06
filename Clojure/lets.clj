(defn overwrite [x]
  (println x)
  (let [x 2]
    (println x)
    (let [x 3]
      (println x)
      (let [x 4]
        (println x))))
  (println x))

(def x 4)
(defn add1 [y] (+ x y))
(defn add2 [y] ((fn [y] (+ x y)) 3))
(defn add3 [y] ((fn [y] ((fn [x] (+ x y)) 3)) 2))

;; KT2
(defn increment [n] (inc' n))
#(inc %)

(defn double-func [n] (* n 2))
#(* % 2)

(defn square [n] (* n n))
#(* % %)

(defn func [num]
  (if (even? num)
    (+ num 2)
    (- (* num 3) 1)))
#(if (even? %) (+ % 2) (- (* % 3) 1))

(defn calc-change'
  [owed given]
  (let [change (- given owed)]
    (if (> change 0) change 0)))
#(let [ch (- %1 %2)] (if (> ch 0) ch 0))

(defn positive-number [n]
  (if-let [num (> n 0)]
    num
    "False"))
#(if-let [n (> %1 0)] n "False")

(defn overwrite [x]
  (println x)
  (let [x 2]
    (println x)
    (let [x 3]
      (println x)
      (let [x 4]
        (println x))))
  (println x))

#((println %)
  (let [% 2] 
    (println %) 
    (let [% 3] 
      (println %) 
      (let [% 4] 
        (println %)))) 
  (println %))