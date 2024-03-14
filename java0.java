// Intersection of Two Arrays
public int[] intersection(int[] nums1, int[] nums2) {
  Set<Integer> set = new HashSet<>();
  for(int i:nums1){
    set.add(i);
  }
  ArrayList<Integer> list = new ArrayList<>();
  for (int i:nums2){
    if(set.contains(i)){
      set.remove(i);
      list.add(i);
    }
  }
  int[] result = new int[list.size()];
  for(int i = 0; i < result.length; i++){
    result[i] = list.get(i);
  }
  return result;
}

// Best Time to Buy and Sell Stock
public int maxProfit(int[] prices) {
  int difference = 0;
  int left = 0;
  int right = 1;
  while(right > left && right < prices.length){
    if(prices[left] > prices[right]){
      left = right;
    }else if(prices[right] - prices[left] > difference){
      difference = prices[right] - prices[left];
    }
    right += 1;
  }
  return difference;
}

// Best Time to Buy and Sell Stock
public int maxProfit(int[] prices) {
  int overallProfit = 0;
  int minPrice = prices[0];
  for(int i:prices){
    if(i > minPrice){
      overallProfit = Math.max(i - minPrice, overallProfit);
    }else{
      minPrice = i;
    }
  }
  return overallProfit;
}

// Majority Element
public int majorityElement(int[] nums) {
  int result = 0;
  int freq = 0;
  for(int i:nums){
    if(freq == 0){
      result = i;
      freq = 1;
    }else if(i == result){
      freq++;
    }else{
      freq--;
    }
  }
  return result;             
}

// Contains Duplicate
public boolean containsDuplicate(int[] nums) {
  Set<Integer> set = new HashSet<>();
  for(int i:nums){
    if(set.contains(i)){
      return true;
    }
    set.add(i);
  }
  return false;
}

// Insert Interval
public int[][] insert(int[][] intervals, int[] newInterval) {
  List<int[]> result = new ArrayList<>();
  int i = 0;
  while(i < intervals.length && intervals[i][1] < newInterval[0]){
    result.add(intervals[i]);
    i++;
  }
  while(i < intervals.length && intervals[i][0] <= newInterval[1]){
    newInterval = new int[]{Math.min(newInterval[0], intervals[i][0]), Math.max(newInterval[1], intervals[i][1])};
    i++;
  }
  result.add(newInterval);
  while(i < intervals.length){
    result.add(intervals[i]);
    i++;
  }
  return result.toArray(new int[result.size()][]);
}

// Insert Interval
public int[][] insert(int[][] intervals, int[] newInterval) {
  List<int[]> resultArr = new ArrayList<>();
  for(int i = 0; i < intervals.length; i++){
    if(newInterval[1] < intervals[i][0]){
      resultArr.add(newInterval);
      newInterval = intervals[i];
    }else if(newInterval[0] > intervals[i][1]){
      resultArr.add(intervals[i]);
    }else{
      newInterval = new int[]{Math.min(newInterval[0],intervals[i][0]),Math.max(newInterval[1],intervals[i][1])};
    }
  }
  resultArr.add(newInterval);
  return resultArr.toArray(new int[resultArr.size()][]);
}

// 3Sum
public List<List<Integer>> threeSum(int[] nums) {
  List<List<Integer>> result = new ArrayList<>();
  Arrays.sort(nums);
  for(int i = 0; i < nums.length - 2; i++){
    if(i > 0 && nums[i] == nums[i - 1]){
      continue; // skip the same result array occurred
    }
    int left = i + 1;
    int right = nums.length - 1;
    while(left < right){
      int threeSum = nums[i] + nums[left] + nums[right];
      if(threeSum < 0){
        left++;
      }else if(threeSum > 0){
        right--;
      }else{
        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
        left++;
        right--;
        while(nums[left] == nums[left - 1] && left < right){
          left++; // skip the same result array occurred
        }
      }
    }
  }
}