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
  :set-token
  validate-spec
  (fn [db [_ value]]
    (if (or (= value "")
            (nil? value))
      (dispatch [:set-active-view "login-component"])
      (do (dispatch [:set-active-view "menu-component"])))
    (assoc db :token value)))

(reg-event-db
  :set-active-view
  validate-spec
  (fn [db [_ value]]
    (if (not (= (:active-view db) value))
      (if (= value "menu-component")
        (dispatch [:set-views-history (list "menu-component")])
        (if (not (= value (first (:views-history db))))
          (dispatch [:set-views-history (conj (:views-history db) value)]))))
    (assoc db :active-view value)))

(reg-event-db
  :trigger-previous-view
  validate-spec
  (fn [db [_ _]]
    (let [history (:views-history db)
          prev-candidate (first (rest history))]
      (if (not (nil? prev-candidate))
        (dispatch [:set-active-view prev-candidate])
        (dispatch [:set-active-view "menu-component"])))
    (assoc db :views-history (rest (:views-history db)))))

(reg-event-db
  :set-courses
  validate-spec
  (fn [db [_ value]]
    (assoc db :courses value)))

(reg-event-db
  :set-active-course-id
  validate-spec
  (fn [db [_ value]]
    (if (not (nil? value))
      (dispatch [:trigger-previous-view]))
    (assoc db :active-course-id value)))

(reg-event-db
  :set-quizzes
  validate-spec
  (fn [db [_ value]]
    (assoc db :quizzes value)))

(reg-event-db
  :set-events
  validate-spec
  (fn [db [_ value]]
    (assoc db :events value)))

(reg-event-db
  :set-event-new-name
  validate-spec
  (fn [db [_ value]]
    (assoc db :event-new-name value)))

(reg-event-db
  :set-event-new-day
  validate-spec
  (fn [db [_ value]]
    (assoc db :event-new-day value)))

(reg-event-db
  :set-event-new-month
  validate-spec
  (fn [db [_ value]]
    (assoc db :event-new-month value)))

(reg-event-db
  :set-event-new-year
  validate-spec
  (fn [db [_ value]]
    (assoc db :event-new-year value)))

(reg-event-db
  :set-event-new-desc
  validate-spec
  (fn [db [_ value]]
    (assoc db :event-new-desc value)))

(reg-event-db
  :set-views-history
  validate-spec
  (fn [db [_ value]]
    (assoc db :views-history value)))

(reg-event-db
  :clear-new-events-state
  validate-spec
  (fn [db [_ _]]
    (assoc (assoc (assoc (assoc (assoc db
                                  :event-new-day 1)
                           :event-new-month 1)
                    :event-new-year 2018)
             :event-new-name nil)
      :event-new-desc nil)))

