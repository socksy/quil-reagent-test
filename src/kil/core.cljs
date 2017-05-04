(ns ^:figwheel-always kil.core
  (:require [reagent.core :as reagent :refer [atom]]
            [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(enable-console-print!)

(def my-text (atom ""))

(def w 800)
(def h 400)

(defn setup []
  {})

(defn update-state [state]
  ())

(defn draw [state]
  (q/background 255)
  (q/fill 0)
  (q/text-font "Fira Sans" "32")
  (q/text @my-text 5 50))

(q/defsketch foo
  :setup  setup
  :update update-state
  :draw   draw
  :host "foo"
  :no-start true
  :middleware [m/fun-mode]
  :size [w h])

(defn atom-input [value]
  [:input {:type "text"
           :value @my-text
           :on-change #(reset! my-text (-> % .-target .-value))}])

(defn hello-world []
  (reagent/create-class
    {:reagent-render (fn [] [:canvas#foo {:width w :height h}])
     :component-did-mount foo}))

(defn base []
  [:div [hello-world]
   [:p {:style {:padding 0}}[atom-input]]])

(reagent/render-component
  [base]
  (. js/document (getElementById "app")))

(defn on-js-reload [])
