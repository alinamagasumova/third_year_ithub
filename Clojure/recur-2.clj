(require '[clojure.core.match :refer [match]])
(require '[defun.core :refer [defun]])

(defun count'
  ([([] :seq)] 0)
  ([xs] (+ 1 (count' (rest xs)))))

;; только для векторов
(defun count''
  ([([] :seq)] 0)
  ([[x & xs]] (+ 1 (count' xs))))

(defun take'
  ([_ ([] :seq)] '())
  ([0 _] '()) 
  ([n ([x & xs] :seq)] (let [tail (take' (- n 1) xs)] 
                         (cons x tail))))

(defun finite-cycle [([head & tail] :seq)] (concat (cons head tail) (list head)))

(defun cycle' [([head & tail] :seq)]
  (lazy-seq (cons head (cycle' (concat tail (list head))))))

;; drop'
(defun drop'
  ([_ ([] :seq)] '())
  ([0 n] n)
  ([n ([x & xs] :seq)] (drop' (- n 1) xs)))

;; drop'' like Igor Olegovich
(defun drop''
  ([_ ([] :seq)] '())
  ([n ([x & xs] :seq)] (if (not= n 0) (drop' (dec n) xs) (cons x xs))))

;; dropWhile'
(defun drop-while'
  ([_ ([] :seq)] '())
  ([exp ([x & xs] :seq)] (if (exp x) (drop-while' exp xs) (cons x xs))))

;; concat'
;; not fully done :(( i don't understand how to do it with more args
(defun concat'
  ([n] (lazy-seq n))
  ([a b] (into a b))
  ([a b & c] (concat' (into a b) c))
  )

;; all (all neg? '(-1 -2 -3)) = true
;;     (all neg? '(-1 -2 2 -3)) = false

(defun all'
  ([_ ([] :seq)] true)
  ([exp ([x & xs] :seq)] (if (exp x) (all' exp xs) false)))

;; any (any neg? '(-1 2 -3)) = true
(defun any'
  ([_ ([] :seq)] false)
  ([exp ([x & xs] :seq)] (if (exp x) true (any' exp xs))))

;; reverse'
(defun reverse' 
  ([([] :seq)] nil) 
  ([([head & tail] :seq)] (lazy-seq (concat (reverse'' tail) (list head)))
   ))

(reverse' [1 2 3])
