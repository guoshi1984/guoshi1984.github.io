import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
public class NearestMovie
{
	public static class Movie
	{
		int id; 
		int rate;
		List<Movie> neighbors;
	
		public Movie(int id, int rate)
		{
			this.id = id;
			this.rate = rate;
			neighbors = new LinkedList<Movie>();
		}
		
		public Movie addNeighbors(Movie mv)
		{
			this.neighbors.add(mv);
			return this;
		}
	}
	
 	static	class CompMovie implements Comparator<Movie>
	{
		public int compare(Movie mv1, Movie mv2)
		{
			if(mv1.rate < mv2.rate) return -1;
			else return 1;
		}
	} 
        static	List<Movie> find(Movie movie, int k)
	{
		Queue<Movie> q = new LinkedList<Movie>();
		PriorityQueue<Movie> pq = new PriorityQueue(k, 
			new CompMovie());
		Set<Movie> visited = new HashSet<Movie>();
		visited.add(movie);
		List<Movie> res = new LinkedList<Movie>();	
		q.offer(movie);
		while(!q.isEmpty())
		{	
			Movie cur = q.poll();
			System.out.println("cur: " + cur.id);
			for(Movie nbr: cur.neighbors)
			{
				if(visited.add(nbr))
				{
					System.out.println("nbr: " + nbr.id);
					pq.offer(nbr);
					if(pq.size()>k)
					{
						pq.poll();
					}	
					q.offer(nbr);
				}
			}
		}
		while(!pq.isEmpty())
		{	
			Movie head = pq.poll();
			res.add(head);
			System.out.println(head.id);
		}
		return res;
		
	}
	public static void main(String[] args)
	{
		Movie mv1 = new Movie(1,1);
		Movie mv2 = new Movie(2,2);
		Movie mv3 = new Movie(3,3);
		Movie mv4 = new Movie(4,4);
		Movie mv5 = new Movie(5,5);
		Movie mv6 = new Movie(6,6);
		Movie mv7 = new Movie(7,7);
		Movie mv8 = new Movie(8,8);
		Movie mv9 = new Movie(9,9);
		Movie mv10 = new Movie(10,10);
		mv1.addNeighbors(mv2).addNeighbors(mv3);	
		mv2.addNeighbors(mv3).addNeighbors(mv1);	
		mv3.addNeighbors(mv1).addNeighbors(mv2);	
		mv4.addNeighbors(mv5).addNeighbors(mv6);	
		mv5.addNeighbors(mv4).addNeighbors(mv6);	
		mv6.addNeighbors(mv4).addNeighbors(mv5);	
		mv7.addNeighbors(mv8).addNeighbors(mv9).addNeighbors(mv10);	
		mv8.addNeighbors(mv9).addNeighbors(mv10).addNeighbors(mv7);	
		mv9.addNeighbors(mv7).addNeighbors(mv8).addNeighbors(mv10);	
		mv10.addNeighbors(mv7).addNeighbors(mv8).addNeighbors(mv9);	
		find(mv7,2);
			
	}
} 
