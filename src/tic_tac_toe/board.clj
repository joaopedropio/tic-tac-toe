(ns tic-tac-toe.board)

(def blank "_")
(def palyer-x "X")
(def palyer-O "O")
(def board-size 3)

(defn- create-key [x y]
  (keyword (str "x" x "y" y)))

(defn- field [x y value]
  {(create-key x y) value})

(defn- coordinate->field [coordinate]
  (let [x (coordinate :x)
        y (coordinate :y)
        value (coordinate :value)]
    (field x y value)))

(defn- field->coordinate [field]
  (let [point (first (keys field))
        x-y (vec (re-seq #"[0-9]+" (str point)))
        x (Integer/parseInt (x-y 0))
        y (Integer/parseInt (x-y 1))]
    {:x x :y y :value (field point)}))

(defn- create-y-axis [board x size]
  (reduce #(conj %1 (field x %2 blank)) board (range size)))

(defn create-board []
  (reduce #(create-y-axis %1 %2 board-size) {} (range board-size)))

(defn show-board [board]
  (println (board :x0y0) (board :x0y1) (board :x0y2))
  (println (board :x1y0) (board :x1y1) (board :x1y2))
  (println (board :x2y0) (board :x2y1) (board :x2y2)))

(defn set-on-board 
  ([board player coordinate]
   (println coordinate)
   (let [x (coordinate 0)
         y (coordinate 1)]
     (println x " " y)
     (set-on-board board player x y)))

  ([board player x y]
   (assoc board (create-key x y) player)))

(defn get-on-board [board x y]
  (get board (create-key x y)))