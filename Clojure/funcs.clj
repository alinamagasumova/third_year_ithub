(def expected-time 40)
(defn remaining-time [expected min]
  (- expected min))
(remaining-time expected-time 20)
(defn prep-time [layers]
  (* layers 2))
(defn total-time [layers min]
  (+ min (prep-times layers)))