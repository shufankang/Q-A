import java.util.LinkedList;
import java.util.Queue;

public class FindPathMaze {
	// backtracking
	// 	1 path 0 wall
		public static int maze3(int[][] grid) {
			int rows = grid.length;
			int cols = grid[0].length;
			int[][] visited = new int[rows][cols];
			if (!dfsHelper(0, 0, grid, visited)) {
				return 0;
			}
			return 1;
		}
		public static boolean dfsHelper(int i, int j, int[][] grid, int[]
				[] visited) {
			if (i >= 0 && i < grid.length && j >= 0 && j <
					grid[0].length && grid[i][j] == 9) {
				visited[i][j] = 1;
				return true;
			}
			if (isSafe(grid, i, j) == true && visited[i][j] == 0) {
				visited[i][j] = 1;
				if (dfsHelper(i - 1, j, grid, visited)) {
					return true;
				}
				if (dfsHelper(i, j + 1, grid, visited)) {
					return true;
				}
				if (dfsHelper(i + 1, j, grid, visited)) {
					return true;
				}
				if (dfsHelper(i, j - 1, grid, visited)) {
					return true;
				}
				visited[i][j] = 0;
				return false;
			}
			return false;
		}
		public static boolean isSafe(int[][] grid, int i, int j) {
			if (i >= 0 && i < grid.length && j >= 0 && j <
					grid[0].length && grid[i][j] == 1) {
				return true;
			}
			return false;
		}
	// 	0 path 1 wall
		public static boolean isSafe2(int[][] grid, int i, int j) {
			if (i >= 0 && i < grid.length && j >= 0 && j <
					grid[0].length && grid[i][j] == 0) {
				return true;
			}
			return false;
		}
		public static void main(String[] args) {
			int[][] grid = {
					{1, 0, 1, 1, 1},
					{1, 1, 0, 0, 1},
					{0, 0, 0, 0, 1},
					{0, 9, 1, 1, 1}};
			System.out.print(maze3(grid));
		}

	// 	iteration bfs
		public static boolean maze2(int[][] grid) {
			if (grid == null) {
				return false;
			}
			Queue<point> queue = new LinkedList<point>();
			int[][] mark = new int[grid.length][grid[0].length];
			int[] dx = {0, -1, 1, 0};
			int[] dy = {1, 0, 0, -1};
			point start = new point(0, 0);
			queue.offer(start);
			while (!queue.isEmpty()) {
				point tmp = queue.poll();
				mark[tmp.x][tmp.y] = 1;
				if (grid[tmp.x][tmp.y] == 9) {
					return true;
				} else if (grid[tmp.x][tmp.y] == 0) {
					continue;
				} else {
					for (int i = 0; i < 4; i++) {
						if (tmp.x + dx[i] >= 0 && tmp.x + dx[i] <
								grid.length && tmp.y + dy[i] >= 0 && tmp.y + dy[i] < grid[0].length) {
							if (mark[tmp.x +dx[i]][tmp.y + dy[i]]
									== 0) {
								queue.offer(new point(tmp.x +
										dx[i], tmp.y + dy[i]));
							}
						}
					}
				}
			}
			return false;
		}
		public static class point {
			int x;
			int y;
			point(int x, int y) {
				this.x = x;
				this.y = y;
			}
		}
	}
	