(ns ply-basic-todo.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:my-list [{:text "take out the trash." :done false}
                                {:text "remember the milk" :done true}
                                {:text "party like its 1999" :done false}]}))

;; todo is a cursor and we are outside of render.
;; so need to use transact!/update! and @ on todo
(defn handle-toggle [todo]
  (om/update! todo [:done] (not (:done @todo))))

(defn todo-component [todo owner]
  (dom/li #js {:className (str "done-" (:done todo))
               :onClick #(handle-toggle todo)}
          (:text todo)))

(defn todo-list-component [app-data owner]
  (dom/div nil
   (dom/h1 nil "My TODO List is Awesome")
           (apply dom/ul nil (om/build-all todo-component (:my-list app-data)))))

assoc

(om/root
 todo-list-component
 app-state
 {:target (. js/document (getElementById "app"))})
