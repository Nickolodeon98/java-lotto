# 기능 목록

# A. 로직 파트

## 주어진 개수에 맞게 로또를 생성하는 메서드들

### 1. int generatedLottoNums(long price)

``매개 변수로 주어진 금액으로 몇 개의 로또를 살 수 있는지 계산한다.``

### 2. List<Integer> lottoGenerator(int count)

``중복되지 않는 무작위의 숫자 6개를 오름차순 정렬된 배열에 넣는다.``

### 3. void everyLottoGenerator(int count)

``무작위 숫자 6개로 구성된 로또들을 생성한다.``

## 당첨 여부 및 등수를 찾는 메서드들

### 4. int howManyDuplicates(List<Integer> numbers, Lotto lotto)

``정수 리스트와 로또 객체 둘 사이에 몇 개의 숫자가 겹치는지 확인한다.``

### 5. Ranks mapDuplicatesToRanks(int duplicatesCount, boolean isBonus)

``주어진 매개변수를 토대로 중복되는 숫자의 개수와 당첨 단계를 매핑해준다.``

### 변경: 6. List<Ranks> rankedWhereAbouts(List<Integer> winningNumbers, int bonus)
``몇 등이 당첨되었는지를 검사한다. 등수 목록을 리스트로 리턴한다.``

[//]: # (클래스로 빼야 하는지 여부를 생각해본다.)

## 수익률을 계산하는 메서드들

### 7. int earnings(List<Ranks> rankings)

``얼마를 벌었는지 계산한다.``

### 8. String profitPercentage(int earnings)

``번 돈을 토대로 수익률을 계산해서 문자열 형태로 리턴한다.``