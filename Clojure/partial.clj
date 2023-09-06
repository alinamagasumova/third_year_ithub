(defn add4 [a b c d] (+ a b c d))

;; (defn add-x-to-3 [x] (fn [b c d] (add4 x b c d)))
;; (def add-to-1 (add-x-to-3 1))

(def add-to-1 (partial add4 1))
(def add-to-1-and-2 (partial add-to-1 2))
(def add-to-1-and-2' (partial add4 1 2))

;;
(defn get-request-url [host api-key resourse id] (str host "/" resourse "/" id "?token=" api-key))

(def url-builder (partial get-request-url "http://ithub.ru"))
(url-builder "5678Fgi87dyrFr5677" "book" "5678")

(def api-builder (partial url-builder "5678Fgi87dyrFr5677"))
(api-builder "video" "357")

(def resourse-builder (partial api-builder "book"))
(resourse-builder "4567")

;; kt 4
(defn if-even [func n] (if (even? n) (func n) n))

(def if-even-inc (partial if-even inc))
(if-even-inc 4)

(def if-even-dbl (partial if-even #(* % 2)))
(if-even-dbl 4)

(def if-even-sqr (partial if-even #(* % %)))
(if-even-sqr 4)

(defn binary-partial [bin-func x] (fn [y] (bin-func x y)))
(def partial-sum (binary-partial + 4))
(partial-sum 2) ;; 6