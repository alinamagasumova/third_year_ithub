
(defn increment [n] (inc n))
(defn double-func [n] (* n 2))
(defn square [n] (* n n))

(defn func [num]
  (if (even? num)
    (+ num 2)
    (- (* num 3) 1)))