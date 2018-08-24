(ns cljoodle.subs
  (:require
    [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-token
  (fn [db _]
    (:token db)))

(reg-sub
  :get-active-view
  (fn [db _]
    (:active-view db)))

(reg-sub
  :get-previous-view
  (fn [db _]
    (:previous-view db)))

(reg-sub
  :get-courses
  (fn [db _]
    (:courses db)))

(reg-sub
  :get-active-course-id
  (fn [db _]
    (:active-course-id db)))

(reg-sub
  :get-quizzes
  (fn [db _]
    (:quizzes db)))

(reg-sub
  :get-events
  (fn [db _]
    (:events db)))

(reg-sub
  :get-event-new-name
  (fn [db _]
    (:event-new-name db)))

(reg-sub
  :get-event-new-day
  (fn [db _]
    (:event-new-day db)))

(reg-sub
  :get-event-new-month
  (fn [db _]
    (:event-new-month db)))

(reg-sub
  :get-event-new-year
  (fn [db _]
    (:event-new-year db)))



