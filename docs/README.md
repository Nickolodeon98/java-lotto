# 기능 목록

## A. 로직 파트

### 1. int generatedLottoNums(long price)

``매개 변수로 주어진 금액으로 몇 개의 로또를 살 수 있는지 계산한다.``

### 2. List<Integer> lottoGenerator(int count)

``중복되지 않는 무작위의 숫자 6개를 오름차순 정렬된 배열에 넣는다.``

### 3. void allLottoGenerator(int count)

``무작위 숫자 6개로 구성된 로또들을 생성한다.``

### 4. int isEarnOrLoss(List<Integer> winningNumbers, long price)
### 변경: 4. int rankedWhereAbouts(List<Integer> winningNumbers)
``몇 등이 당첨되었는지를 검사한다. 등수를 리턴한다.``

[//]: # (클래스로 빼야 하는지 여부를 생각해본다.)

### 5. int howManyDuplicates(List<Integer> numbers, Lotto lotto)

``정수 리스트와 로또 객체 둘 사이에 몇 개의 숫자가 겹치는지 확인한다.``