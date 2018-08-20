(ns cljoodle.subs
  (:require
    [re-frame.core :refer [reg-sub]]))


(reg-sub
  :get-greeting
  (fn [db _]
    (:greeting db)))

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
