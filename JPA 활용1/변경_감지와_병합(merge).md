# 변경 감지와 병합(merge)
## 준영속 엔티티
- 영속성 컨텍스트가 더이상 관리하지 않는 엔티티

## 준영속 엔티티 수정 방법
1. 변경 감지 기능 사용
2. 병합(merge) 사용
### 변경 감지 기능 사용
- 영속성 컨텍스트에 엔티티를 다시 조회한 후에 데이터를 수정하는 방법
- 트랜잭션 안에서 엔티티를 다시 조회하고 값 변경 -> 트랜잭션 커밋 시점에 변경 감지(Dirty Checking) 기능이 동작하여 DB에 Update SQL을 자동으로 날려준다.
```java
@Transactional
void update(Item item) {
    Item findItem = em.find(Item.class, item.getId());

    findItem.setPrice(item.getPrice)
}
```
### 병합 사용
- 준영속 상태의 엔티티를 영속 상태로 변경할 때 사용  
**병합 동작 방식**
1. 준영속 엔티티의 식별자 값으로 영속 엔티티를 조회
2. 영속 엔티티의 값을 준영속 엔티티의 값으로 모두 교체(병합)한다.
3. 트랜잭션 커밋 시점에 변경 감지 기능이 동작하여 DB에 Update SQL을 날린다.
```java
@Transactional 
void update(Item item) { //item : 준영속 상태의 엔티티
    Item mergeItem = em.merge(item);
}
     
```
## 결론 : 엔티티를 사용할 떄는 항상 변경 감지를 사용하자
- 변경 감지 기능을 사용하면 원하는 속성만 선택해서 변경 가능
- 병합을 사용하면 모든 속성이 변경 -> 병합시 값이 없으면 null로 업데이트 될 위험이 있다.  
- 실무에서는 업데이트하는 컬럼이 제한적이기 떄문에 모든 필드를 변경해버리고, 데이터가 없는 필드는 null로 업데이트하는 병합을 사용하는 것은 번거롭다.  
