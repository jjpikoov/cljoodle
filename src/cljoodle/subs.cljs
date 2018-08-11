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


