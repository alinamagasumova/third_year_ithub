(defn sum-square-or-square-sum [x y] 
  (if (> (+ (Math/pow x 2) (Math/pow y 2)) (Math/pow (+ x y) 2))
    (+ (Math/pow x 2) (Math/pow y 2))
    (Math/pow (+ x y) 2)))

(defn sqr [n] (* n n))
(defn sum-square-or-square-sum' [x y]
  (if (> (+ (sqr x) (sqr y)) (sqr (+ x y)))
    (+ (sqr x) (sqr y))
    (sqr (+ x y))))

(defn sum-square-or-square-sum'' [x y]
  (let [sum-square (+ (sqr x) (sqr y))
        square-sum (sqr (+ x y))]
    (if (> sum-square square-sum) sum-square square-sum)))


(defn body [sum-square square-sum] 
  (if (> sum-square square-sum) sum-square square-sum))
(defn sum-square-or-square-sum''' [x y]
  (body (+ (sqr x) (sqr y)) (sqr (+ x y))))

(def body' 
  (fn [sum-square square-sum]
    (if (> sum-square square-sum) sum-square square-sum)))
(defn sum-square-or-square-sum'''' [x y]
  (fn [sum-square square-sum]
    (if (> sum-square square-sum) sum-square square-sum)) (+ (sqr x) (sqr y)) (sqr (+ x y)))

;; ((fn [x] x) 2)
;; #(%) 2
;; (fn [x y] (+ x y))
;; #(+ %1 %2)

(defn sum-square-or-square-sum''''' [x y]
  #(if (> %1 %2) %1 %2) (+ (sqr x) (sqr y)) (sqr (+ x y)))