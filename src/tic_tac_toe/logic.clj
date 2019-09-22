(ns tic-tac-toe.logic
  (:require [tic-tac-toe.board :refer :all]))

(def positions [[[0 0] [0 1] [0 2]]
                [[0 0] [1 0] [2 0]]
                [[0 0] [1 1] [2 2]]
                [[1 0] [1 1] [1 2]]
                [[0 1] [1 1] [2 1]]
                [[2 0] [2 1] [2 2]]
                [[0 2] [1 2] [2 2]]
                [[0 2] [1 1] [2 0]]])

(defn get-line [board points]
  (map #(get-on-board board (% 0) (% 1)) points))

(defn lines [board positions]
  (map #(get-line board %) positions))

(defn same-player [line]
  (if (zero? (count line))
    false
    (do 
      (let [p1 (line 0)
            p2 (line 1)
            p3 (line 2)]
        (= p1 p2 p3)))))

(defn remove-invalid-lines [lines]
  (filter #(not (contains? (set %) "_")) lines))

(defn winner? [board]
  (-> (lines board positions)
      remove-invalid-lines
      first
      vec
      same-player))

(defn get-winner [board]
  (-> (lines board positions)
      remove-invalid-lines
      first
      vec
      (get 0)))

(defn next-player [board]
  (let [plays (frequencies (filter #(not= % "_") (vals board)))
        player-1-count (plays "X")
        player-2-count (plays "O")]
    (if (= player-1-count player-2-count)
      "X"
      "O")))

(defn draw? [board]
  (empty? (filter #(= % "_") (vals board))))

(frequencies(vals(-> (create-board)
                (set-on-board "O" 2 0)
                (set-on-board "O" 2 1)
                (set-on-board "O" 2 2))))

(next-player (create-board))

(draw? (create-board))