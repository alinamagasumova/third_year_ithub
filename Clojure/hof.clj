(require '[defun.core :refer [defun]])
;; map fn [] - applies fn to every item in []
;; (map reverse ["dog" "cat "elefant"])

(map #(str % "!") ["dog" "cat" "elefant"])

(defun add-bang
  ([([] :seq)] '())
  ([([x & xs] :seq)] (cons (str x "!") (add-bang xs))))

(defun map'
  ([f ([] :seq)] '())
  ([f ([x & xs] :seq)] (cons (f x) (map' f xs))))

;; (zip '(1 2 3) '(4 5 6)) // ('(1 4) '(2 6) '(3 6))
;; (map list '(1 2 3) '(4 5 6))

(defn multi-args [& args] ((println args)))
(defn multi-args' [op & args] (apply op args))

;; filter fn:bool []
;; (filter even? [1 2 3 4]) // (2 4)
;; (filter #(= /a (first %)) ["abc" "rtg" "anc" "fng"]) // ("abc" "anc")

(defun filter'
  ([f ([] :seq)] '())
  ([f ([x & xs] :seq)] (if (f x) (cons x (filter' f xs)) (filter' f xs))))

;; reduce + 0 [1 2 3] // 6
;;        binfn start-pos seq
;; reduce + 4 [1 2 3] // 10
(defn concat-str [xs] (reduce str "" xs))
(concat-str ["a" "l" "i" "n" "a"])

(defn sum-of-squares [xs] (reduce + 0 (map #(Math/pow % 2) xs)))
(sum-of-squares [1, 2, 3, 4, 5])

(defn rcons [xs y] (cons y xs))
(defn reverse' [xs] (reduce rcons '() xs))
(reverse' [1 2 3]) ;; (3 2 1)

(defun reduce'
  ([f init ([] :seq)] init)
  ([f init ([x & xs] :seq)] (recur f (f init x) xs)))
(reduce' + 0 [1 2 3])
(defn reduce'' [f init coll] (if (empty? coll) 
                               init 
                               (recur f (f init (first coll)) (rest coll))))

;; SAMOSTOYALKA
;; make fn remove, which delets elems, that pass test
;; (remove even? [1 2 3 4 5]) // (1 3 5)
(defun remove'
  ([_ ([] :seq)] '())
  ([f ([x & xs] :seq)] (if (f x) (remove' f xs) (cons x (remove' f xs)))))
(remove' even? [1 2 3 4 5])
;; zip for 2 elems. with map list and without it
;; (zip '(1 2 3) '(4 5 6)) // ('(1 4) '(2 5) '(3 6))
;; (zip '(1 2) '(4 5 6)) // ((1 4) (2 5))
(defun zip-map [([h & t] :seq) ([x & xs] :seq)] (map list (cons h t) (cons x xs)))
(zip-map '(1 2) '(4 5 6))
(defun zip'
  ([([] :seq) _] '())
  ([_ ([] :seq)] '())
  ([([h & t] :seq) ([x & xs] :seq)] (cons (list h x) (zip' t xs))))
(zip'  '(4 5 6) '(1 2 3))
;; (unzip '((1 4) (2 6) (3 6))) // ((1 2 3) (4 5 6))
(defun unzip
  ([([] :seq)] '())
  ([([h & t] :seq)] (list (map first (cons h t)) (map second (cons h t)))))
;; made by IO
(defun unzip'
  ([([] :seq)] '())
  ([([x y] :seq) & zs] (list
                        (cons x (first (unzip' zs)))
                        (cons y (second (unzip' zs))))))
(unzip '((1 4) (2 5) (3 6)))
;; (zip-with #(+ %1 %2) '(1 2 3) '(4 5 6)) // (5 7 9)
(defun zip-with
  ([_ ([] :seq) _] '())
  ([_ _ ([] :seq)] '())
  ([f ([h & t] :seq) ([x & xs] :seq)] (cons (f h x) (zip-with f t xs))))
(zip-with #(+ %1 %2) '(1 2 3) '(4 5 6))

;; KT
(defun list-contains?
  ([([] :seq) _] '())
  ([([h & t] :seq) idx] (if (or (< idx (count (cons h t))) (> idx (count (cons h t)))) false true))
  )
(list-contains? '(1 2 4 3) -5)

(defn is-palindrome [raw-str] 
  (let [str (filter #(not= % \space) (seq (clojure.string/lower-case raw-str)))]
    (= str (reverse str))))
(is-palindrome "А роза упала на лапу Азора")

(defn harmonic [n] (reduce + 0 (map #(/ 1 %) (range 2 (+  2 n)))))
(harmonic 5)