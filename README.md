# java-blackjack

블랙잭 미션 저장소

## 기능 요구 사항

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## 개념

- 카드
    - 네 가지 타입(TWO ~ TEN, KING, QUEEN, JACK, ACE)
    - 각 종류 별 모든 카드 (스페이드 Ace ....)
- 게임 참가자
    - 플레이어
    - 딜러
- 블랙잭 규칙
    - 승패를 따지고
    - 더한값이 -> 블랙잭 규칙을 맡은 객체가 값을 넘겨받으면 더 뽑을수 있는지
    - List<Card> or Cards 인자로 주면 계산을 하는, 분기 처리(Ace 면 1 or 11, J Q K 는 10 으로 처리)
    - 점수가 같으면 카드 수가 더 많은 쪽이 이기고, 카드 수까지 같으면 무승부이다.

## 구현 목록

- 카드 타입
    - 스페이드, 하트, 클로버, 다이아몬드
- 카드 숫자
    - ACE, TWO, THREE, .... K
- 카드
- 카드뭉치(일급 컬렉션 느낌일것 같은데)
    - [x] 모든 종류의 카드를 가지도록 초기화한다.
    - [x] 랜덤으로 N 개의 카드를 반환(반환한 카드는 리스트에서 제거)
- 카드들
    - [x] 카드 숫자의 합을 반환한다.
- 딜러
    - [x] 카드 뭉치에서 N 개의 카드를 뽑아 반환한다.
    - [x] 자신의 카드를 N 개 뽑는다.
    - [x] 자신이 가진 카드의 합을 반환한다.
    - [x] 카드를 추가로 뽑아야 한다면, 추가로 한장 더 뽑은 후, 뽑았는지 여부를 반환한다.
- 플레이어
    - [ ] 자신이 가진 카드의 합을 반환한다.
- 블랙잭 규칙
    - [ ] 카드의 합이 N 이하인지 확인한다.
    - [ ] 딜러가 카드를 추가로 뽑아야하는지 알려준다.
    - [ ] 딜러와 플레이어들을 통해 승패를 계산한다.
        - 점수가 같으면 카드 수가 더 많은 쪽이 이기고, 카드 수까지 같으면 무승부이다.

## 기능 분석

1. 랜덤으로 카드를 뽑는다. (x)
2. 딜러가 게임 참가자(플레이어, 딜러) 모두에게 카드를 배분한다. (x)
3. 게임 참가자(플레이어, 딜러)의 카드를 모두 더한다. (x)
    - Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
4. 카드의 합이 16이하 인지 확인한다. (x)
5. 게임 참가자(플레이어, 딜러)는 추가로 카드를 뽑을 수 있다. (x)
6. 딜러는 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. (x)
7. 최종 승패를 계산한다 (x)

- 플레이어는 딜러와만 승패를 계산한다
- 딜러는 모든 플레이어와 승패를 계산한다
