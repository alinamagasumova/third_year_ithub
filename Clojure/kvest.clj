(def knight-awake? false)
(def archer-awake? true)
(def prisoner-awake? true)
(def dog-present? true)

(defn can-fast-attack? [knight-awake?] 
  (if knight-awake? false true))

(defn can-spy? [knight-awake? archer-awake? prisoner-awake?]
  (if knight-awake? 
    true 
    (if archer-awake? 
      true 
      (if prisoner-awake? true false))))

(defn can-signal-prisoner? [archer-awake? prisoner-awake?]
  (if archer-awake? false 
      (if prisoner-awake? true false)))

(defn can-free-prisoner? [knight-awake? archer-awake? prisoner-awake? dog-present?]
  (if dog-present? 
    (if archer-awake? false true) 
    (if prisoner-awake?
      (if knight-awake? false
          (if archer-awake? false true)) 
      false)))