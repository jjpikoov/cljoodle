;;; File declares component for showing quizes for given course
(ns cljoodle.android.components.quizzes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.navigator-component :as nav]
    [cljoodle.android.components.common.menu-list-component :as menu-list]
    [cljoodle.android.components.common.styles :as styles]
    [cljoodle.converter.quizzes-converter :as qc]
    [cljoodle.http.quizzes :as quiz]
    [reagent.core :as r]))

(defn quizzes-component
  "Function returns Hiccup data for showing quizzes.

  If user has not set course it dispatches change of component to courses-component,
  if course is already chosen it fetches data from Moodle and generates list of quizes"
  []
  (let
    ; subscribe
    [course-id (subscribe [:get-active-course-id])
     quizzes-final (subscribe [:get-quizzes])
     token (subscribe [:get-token])
     ; util functions
     converting-function (partial qc/convert-quiz-to-menu-item-format
                                  (fn [id] (fn [] nil)))
     converted-quizes (map converting-function @quizzes-final)]
    ; fetch data
    (if (nil? @course-id)
      (dispatch [:set-active-view "courses-component"])
      (do
        (if (nil? @quizzes-final)
          (quiz/get-quizzes
            #(dispatch [:set-quizzes %])
            @token
            @course-id))))
    ; render
    [rw/view (nav/navigator-component "Quizzes")
     [rw/view styles/item-list-container-style
      [rw/text {:style {:font-size     20
                        :font-weight   "100"
                        :margin-bottom 20
                        :text-align    "center"}} "Choose quiz"]
      (into [rw/scroll-view]
            (menu-list/menu-list-component converted-quizes))]]))
