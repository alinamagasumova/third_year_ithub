(def sample-data
  [[24.2 420031]
   [25.8 492657]
   [25.9 589014]
   [23.8 691995]
   [24.7 734902]
   [23.2 794243]
   [23.1 836204]
   [23.5 884120]])

(defn local-max? [[a b c]]
  (and (< (first a) (first b)) (< (first c) (first b))))

(defn local-min? [[a b c]]
  (and (> (first a) (first b)) (> (first c) (first b))))

(defn local-max'? [[[a _] [b _] [c _]]]
  (and (< a b) (< c b)))
(defn local-min'? [[[a _] [b _] [c _]]]
  (and (> a b) (> c b)))

(local-max'? (take 3 sample-data))
(local-min'? (take 3 sample-data))
(local-min'? (take 3 (drop 2 sample-data)))

(defn points [data] 
  (lazy-seq (let [current (take 3 data)]
              (cond 
                (< (count current) 3) '()
                (local-max? current) (cons (conj (second current) :peak) (points (rest data)))
                (local-min? current) (cons (conj (second current) :valley) (points (rest data)))
                :otherwise (points (rest data))))))
(points sample-data)