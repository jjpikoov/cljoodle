(ns cljoodle.converter.events-converter)

(defn- epoch-time-to-string
  [epoch-time]
  (.toString (js/Date. (* epoch-time 1000))))

(defn date-to-epoch-int
  [year month day]
  (/ (.getTime (js/Date. year, (dec month), day)) 1000))

(defn convert-event-to-menu-item-format
  [event]
  {:name        (:name event)
   :description (:description event)
   :timestart   (epoch-time-to-string (:timestart event))
   :id          (:id event)})
