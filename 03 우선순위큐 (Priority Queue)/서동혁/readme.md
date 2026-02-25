# 1. PriorityQueue란?

- 일반적인 Queue와 달리 데이터의 **우선 순위**에 따라 요소를 추출하는 자료구조이며 내부적으로 이진 Heap 자료구조를 사용하여 구현되어 있어 시간복잡도에 대해서 매우 유리한 자료구조이다.

- `PriorityQueue`는 기본적으로 **오름차순(낮은 숫자가 우선순위가 높음)**으로 정렬되며, 이를 `최소 힙(Min Heap)` 방식이라고 한다.

## 핵심 메서드
- `add() / offer()` : 요소를 추가(자동으로 Heap 정렬이 됨)
- `poll()/ remove()` : 우선순위가 가장 높은 요소를 꺼내고 제거(자동으로 Heap 정렬이 됨)
- `peek() / element()` : 우선순위가 가장 높은 요소를 확인만 하고 제거는 X
  - peek()은 null을 반환
  - element()는 NoSuchElementException 발생
  - peek()이 더 유리함

## PriorityQueue 메서드별 시간 복잡도

| 작업 유형 | 메서드 (예외/값 반환) | 시간 복잡도 | 비고 |
| :--- | :--- | :--- | :--- |
| **삽입 (Insert)** | `add(e)` / `offer(e)` | **$O(\log n)$** | 힙 트리 재구조화 발생 |
| **삭제 (Remove)** | `remove()` / `poll()` | **$O(\log n)$** | 루트 제거 후 재구조화 발생 |
| **확인 (Examine)** | `element()` / `peek()` | **$O(1)$** | 루트 노드 즉시 반환 |
| **특정값 삭제** | `remove(object)` | **$O(n)$** | 순차 탐색 후 삭제 필요 |

<br>

# 2. Heap으로 이용하는 법

## 기본 인스턴스 생성
```java
//최소 힙(작은 값이 우선순위 높음)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// 최대 힙(큰 값이 우선순위 높음)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

## 람다식 사용
```java
// 최대 힙(큰 값이 우선순위 높음)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

## compare 메서드 구현
String 비교를 할 때는 compareTo() 사용이 중요
```java
PriorityQueue<Student> pq = new PriorityQueue<>(new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        // 1순위 -> 성적 내림차순(높은 값일수록 우선순위 높음)
        if (s1.score != s2.score) {
            return s2.score - s1.score;
        }

        // 2순위 -> 성적이 같은 경우 이름의 오름차순
        return s1.name.compareTo(s2.name);
    }
}
```

## Comparator.comparing() 활용
```java
PriorityQueue<Student> pq = new PriorityQueue<>(
    Comparator.comparingInt(Student::getScore).reversed() // 1순위: 점수 내림차순
              .thenComparing(Student::getName)            // 2순위: 이름 오름차순
              .thenComparingInt(Student::getAge)          // 3순위: 나이 오름차순
);
```