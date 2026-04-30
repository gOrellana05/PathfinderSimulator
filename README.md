# PathfindingSimulator
A Java-based visual pathfinding simulator that demonstrates how classic graph search algorithms such as A*, BFS, DFS, and Dijkstra operate on a grid-based environment with weighted terrain.
The application provides a real-time visualization of how each algorithm explores the search space to find a path between a start node and a goal node.

---

## Features
- Interactive creation of mazes, allowing to place walls and set the start/goal node on a grid
- Algorithm selector to choose between A*, BFS, DFS, and Dijkstra
- Real-time tracking of the selected algorithm's execution path
- Representation of the final calculated path 
- The UI includes buttons to start the auto-solve process, pause it, advance step-by-step, or reset the process.

---

## How it works
1. Place walls and set the start/goal nodes on the interactive grid
2. Select the algorithm which will solve the maze
3. Control the flow by starting the auto-solve process, pausing it, resetting it or stepping through the process
4. Visualize the result watching the real-time execution followed by the final calculated path highlight

---

## Project structure
```
PathfinderSimulator/

├── src/

│ ├── algorithms/

│ ├── engine/ 

│ ├── ui/ 

│ └── model/

└── README.md
```

---

## Author
Guillermo Orellana Escobar

https://github.com/gOrellana05
