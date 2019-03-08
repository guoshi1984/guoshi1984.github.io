import java.util.*;
public class HighestFive
{
	public static class Record
	{
		public int id;
		public int score;
		public Record(int id, int score)
		{
			this.id = id;
			this.score = score;
		}

	}

	public static class CompareRecord implements Comparator<Record>
	{
		@Override
		public int compare(Record r1, Record r2)
		{
			return r1.score - r2.score;
		}
	}	

	public static Map<Integer, Double> find(Record[] records, int k)
	{
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		if(records == null || records.length == 0)
			return result;
		HashMap<Integer, PriorityQueue<Record>> map = new HashMap<Integer, PriorityQueue<Record>>();
		for(Record r: records)
		{
			if(!map.containsKey(r.id))
			{
				PriorityQueue<Record> pq = new PriorityQueue<Record>(k, new CompareRecord());
				map.put(r.id, pq);
			}
			map.get(r.id).add(r);
			if(map.get(r.id).size() > k)
			{
				map.get(r.id).poll();
			}
		}

		for(Map.Entry<Integer, PriorityQueue<Record>> entry: map.entrySet())
		{
			int id = entry.getKey();
			PriorityQueue<Record> pq = entry.getValue();
			double average = 0;
			int num = pq.size();
			while(!pq.isEmpty())
			{
				average += pq.poll().score;

			}
			average /= num;
			result.put(id, average);
		}
		return result;
	}

	public static void main(String[] args)
	{
		Record r1 = new Record(1, 9);
		Record r2 = new Record(2, 10);
		Record r3 = new Record(3, 8);
		Record r4 = new Record(2, 8);
		Record r5 = new Record(3, 9);
		Record r6 = new Record(1, 10);
		Record r7 = new Record(2, 8);
		Record r8 = new Record(2, 7);
		Record r9 = new Record(1, 9);
		Record r10 = new Record(1, 9);
		Record r11 = new Record(3, 8);
		Record r12 = new Record(2, 10);
		Record r13 = new Record(3, 8);
		Record[] records = {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13};
		System.out.println(find(records, 3));
	}
}
