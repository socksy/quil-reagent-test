(ns ^:figwheel-always kil.core
  (:require [reagent.core :as reagent :refer [atom]]
            [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(enable-console-print!)

(def w 400)
(def h 400)

(defn setup []
  {:t 1})

(defn update [state]
  (update-in state [:t] inc))

(defn draw [state]
  (q/background 255)
  (q/fill 0)
  (q/ellipse (rem (:t state) w) 46 55 55))

(q/defsketch foo
  :setup  setup
  :update update
  :draw   draw
  :host "foo"
  :no-start true
  :middleware [m/fun-mode]
  :size [w h])

(defn hello-world []
  (reagent/create-class
    {:reagent-render (fn [] [:canvas#foo {:width w :height h}])
     :component-did-mount foo}))

(reagent/render-component
  [hello-world]
  (. js/document (getElementById "app")))

(defn on-js-reload [])

