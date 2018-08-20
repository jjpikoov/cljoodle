(ns cljoodle.events
  (:require
    [re-frame.core :refer [reg-event-db after dispatch]]
    [clojure.spec.alpha :as spec]
    [cljoodle.db :as db :refer [app-db]]
    [reagent.core :as r]))


;; -- Interceptors ------------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/blob/master/docspec/Interceptors.md
;;

(defn check-and-throw
  "Throw an exception if db doesn't have a valid spec."
  [spec db [event]]
  (when-not (spec/valid? spec db)
    (let [explain-data (spec/explain-data spec db)]
      (throw (ex-info (str "Spec check after " event " failed: " explain-data) explain-data)))))

(def validate-spec
  (if goog.DEBUG
    (after (partial check-and-throw ::db/app-db))
    []))


;; -- Handlers --------------------------------------------------------------

(reg-event-db
  :initialize-db
  validate-spec
  (fn [_ _]
    app-db))

(reg-event-db
  :set-greeting
  validate-spec
  (fn [db [_ value]]
    (assoc db :greeting value)))

(reg-event-db
  :set-token
  validate-spec
  (fn [db [_ value]]
    (if (or (= value "")
            (nil? value))
      (dispatch [:set-active-view "login-component"])
      (dispatch [:set-active-view "menu-component"]))
    (assoc db :token value)))

(reg-event-db
  :set-active-view
  validate-spec
  (fn [db [_ value]]
    (dispatch [:set-previous-view (:active-view db)])
    (assoc db :active-view value)))

(reg-event-db
  :set-previous-view
  validate-spec
  (fn [db [_ value]]
    (assoc db :previous-view value)))
