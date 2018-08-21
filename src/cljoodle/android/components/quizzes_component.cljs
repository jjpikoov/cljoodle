(ns cljoodle.android.components.quizzes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.navigator-component :as nav]
    [cljoodle.android.components.common.menu-list-component :as menu-list]
    [cljoodle.android.components.common.styles :as styles]
    [cljoodle.converter.quizzes-converter :as qc]
    [cljoodle.http.quizzes :as quiz]
    ))

(defn quizzes-component
  []
  (let
    [course-id (subscribe [:get-active-course-id])
     token (subscribe [:get-token])]
    (if (nil? @course-id)
      (dispatch [:set-active-view "courses-component"])
      (quiz/get-quizzes #(dispatch [:set-quizzes %])
                        @token
                        @course-id))
    [rw/view (nav/navigator-component "Quizzes")
     [rw/view styles/items-list-container-style
      [rw/text {:style {:font-size     20
                        :font-weight   "100"
                        :margin-bottom 20
                        :text-align    "center"}} "Choose quiz"]
      (into [rw/scroll-view]
            (menu-list/menu-list-component
              (let
                [quizzes (subscribe [:get-quizzes])
                 converting-function (partial qc/convert-quiz-to-menu-item-format
                                              (fn [id]
                                                (fn [] (prn id))))]
                (map converting-function @quizzes))))
      ]]))
