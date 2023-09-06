;; Замыкания
(defn if-even [func n] (if (even? n) (func n) n))

;; (defn if-even-inc [n] (if-even #(+ % 1) n))
;; (defn if-even-double [n] (if-even #(* % 2) n))
;; (defn if-even-square [n] (if-even #(* % %) n))

(defn gen-if-even [func] #(if-even func %))
(defn gen-if-even-x [x] (fn [func] (if-even func x)))
((gen-if-even-x 6) inc)

;; http://it-lib.ithub.ru/bbook/1234?token=5678Fgi87dyrFr5677
(defn get-request-url [host api-key resourse id] (str host "/" resourse "/" id "?token=" api-key))

(defn gen-host-request-builder [host] (fn [api-key resourse id] (get-request-url host api-key resourse id)))
(def url-builder (gen-host-request-builder "http://ithub.ru"))
;; (url-builder "5678Fgi87dyrFr5677" "book" "5678")

(defn gen-api-request-builder [host-builder api-key] (fn [resourse id] (host-builder api-key resourse id)))
(def api-builder (gen-api-request-builder url-builder "5678Fgi87dyrFr5677"))
(api-builder "video" "357")

(defn gen-resourse-builder [api-builder resourse] (fn [id] (api-builder resourse id)))
(def resourse-builder (gen-resourse-builder api-builder "book"))
(resourse-builder "4567")