(ns cljoodle.converter.events-converter)

(defn- epoch_time_to_string
  [epoch-time]
  (.toString (js/Date. (* epoch-time 1000))))

(defn date_to_epoch_int
  [year month day]
  (/ (.getTime (js/Date. year, (dec month), day)) 1000))

(defn convert-event-to-menu-item-format
  [event]
  {:name        (:name event)
   :description (:description event)
   :timestart   (epoch_time_to_string (:timestart event))
   :id          (:id event)})
