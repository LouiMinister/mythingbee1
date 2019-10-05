import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
from sklearn.neighbors import KNeighborsClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.model_selection import train_test_split
from sklearn.datasets import make_classification
from sklearn.decomposition import PCA
from imblearn.over_sampling import SMOTE
from IPython.display import display

from sklearn.utils import shuffle

sns.set()
train = pd.read_csv('train_ai2.csv')
test = pd.read_csv('test_ai3.csv')
#print(train.info())
print(train.head())


def pie_chart(feature):
    feature_ratio=train[feature].value_counts(sort=False)
    feature_size = feature_ratio.size
    feature_index = feature_ratio.index
    survived = train[train['safety_rate'] > 3][feature].value_counts()
    dead = train[train['safety_rate'] < 3 ][feature].value_counts()
    plt.plot(aspect='auto')
    plt.pie(feature_ratio, labels=feature_index, autopct='%1.1f%%')
    plt.title(feature + '\'s ratio in total')
    plt.show()
    for i, index in enumerate(feature_index):
        plt.subplot(1, feature_size + 1, i + 1, aspect='equal')
        plt.pie([survived[index], dead[index]], labels=['safe', 'unsafe'], autopct='%1.1f%%')
        plt.title(str(index) + '\'s ratio')
    plt.show()

#pie_chart("convenience")
#
# train_test_data = [train]
# for dataset in train_test_data:
#     print(dataset['safety_rate'])
#
#     dataset.loc[dataset['safety_rate'] < 3, 'safety_rate'] = 0
#     dataset.loc[dataset['safety_rate'] == 3, 'safety_rate'] = 1
#     dataset.loc[dataset['safety_rate'] >3, 'safety_rate'] = 2
#
#
#     dataset['safety_rate'] = dataset['safety_rate'].astype(int)
#     print(dataset['safety_rate'])

# for dataset in train_test_data:
#     dataset['Title'] = dataset.Name.str.extract(' ([A-Za-z]+)\.')
#
# for dataset in train_test_data:
#     dataset['Title'] = dataset['Title'].replace(['Capt', 'Col', 'Countess', 'Don','Dona', 'Dr', 'Jonkheer',
#                                                  'Lady','Major', 'Rev', 'Sir'], 'Other')
#     dataset['Title'] = dataset['Title'].replace('Mlle', 'Miss')
#     dataset['Title'] = dataset['Title'].replace('Mme', 'Mrs')
#     dataset['Title'] = dataset['Title'].replace('Ms', 'Miss')
#
# train[['Title', 'Survived']].groupby(['Title'], as_index=False).mean()
#
#
# for dataset in train_test_data:
#     dataset['Title'] = dataset['Title'].astype(str)
#
# for dataset in train_test_data:
#     dataset['Sex'] = dataset['Sex'].astype(str)
#
# for dataset in train_test_data:
#     dataset['Age'].fillna(dataset['Age'].mean(), inplace=True)
#     print(dataset['Age'].isnull().sum())
#     dataset['Age'] = dataset['Age'].astype(int)
#     train['AgeBand'] = pd.cut(train['Age'], 5)
#
# for dataset in train_test_data:
#
#     dataset.loc[ dataset['safety_rate'] >= 3, 'safety_rate'] = 1
#     dataset.loc[dataset['safety_rate'] < 3, 'safety_rate'] = 0
#     dataset['safety_rate'] = dataset['safety_rate'].astype(int)
#     dataset['Age'] = dataset['Age'].map( { 0: 'Child',  1: 'Young', 2: 'Middle', 3: 'Prime', 4: 'Old'} ).astype(str)
#
# for dataset in train_test_data:
#     dataset['Fare'] = dataset['Fare'].fillna(13.675)
#
#
# train['FareBand'] = pd.qcut(train['Fare'], 5)
# train[['FareBand', 'Survived']].groupby(['FareBand'], as_index=False).mean()
#
# for dataset in train_test_data:
#     dataset.loc[ dataset['Fare'] <= 7.854, 'Fare'] = 0
#     dataset.loc[(dataset['Fare'] > 7.854) & (dataset['Fare'] <= 10.5), 'Fare'] = 1
#     dataset.loc[(dataset['Fare'] > 10.5) & (dataset['Fare'] <= 21.679), 'Fare']   = 2
#     dataset.loc[(dataset['Fare'] > 21.679) & (dataset['Fare'] <= 39.688), 'Fare']   = 3
#     dataset.loc[ dataset['Fare'] > 39.688, 'Fare'] = 4
#     dataset['Fare'] = dataset['Fare'].astype(int)
#
# for dataset in train_test_data:
#     dataset["Family"] = dataset["Parch"] + dataset["SibSp"]
#     dataset['Family'] = dataset['Family'].astype(int)
#
#test = test.drop(features_drop, axis=1)
# train = train.drop(['light','dist_value','edge_id', 'addr', 'land_type','police'], axis=1)
# test = test.drop(['light','dist_value','addr', 'land_type', 'police'], axis=1)
train = train.drop(['edge_id', 'addr', 'land_type','police'], axis=1)
test_data = test.drop(['addr', 'land_type', 'police','safety_rate'], axis=1)

#train = pd.get_dummies(train)
#test = pd.get_dummies(test)

train_label = train['safety_rate']
train_data = train.drop('safety_rate', axis=1)
test_data = test_data.drop("edge_id", axis=1).copy()

# print(train_data.shape, train_label.shape, test_data.shape)
#
train_data, train_label = shuffle(train_data, train_label, random_state = 5)
X_train, X_test, y_train, y_test = train_test_split(
    train_data, train_label, random_state=0)

# 훈련 세트에서 특성별 최솟값 계산
min_on_training = X_train.min(axis=0)
# 훈련 세트에서 특성별 (최댓값 - 최솟값) 범위 계산
range_on_training = (X_train - min_on_training).max(axis=0)

# 훈련 데이터에 최솟값을 빼고 범위로 나누면
# 각 특성에 대해 최솟값은 0, 최대값은 1입니다.
X_train_scaled = (X_train - min_on_training) / range_on_training
print("특성별 최소 값\n{}".format(X_train_scaled.min(axis=0)))
print("특성별 최대 값\n {}".format(X_train_scaled.max(axis=0)))


X_test_scaled = (X_test - min_on_training) / range_on_training
#
#
# sm = SMOTE(ratio='auto', kind='regular')
# X_resampled, y_resampled = sm.fit_sample(X_train_scaled,list(y_train))
# print("After OverSampling, counts of label '1': {}".format(sum(y_resampled==1)))
# print("After OverSampling, counts of label '3': {}".format(sum(y_resampled==2)))
# print("After OverSampling, counts of label '3': {}".format(sum(y_resampled==3)))
# print("After OverSampling, counts of label '3': {}".format(sum(y_resampled==4)))
# print("After OverSampling, counts of label '3': {}".format(sum(y_resampled==5)))

#
# # Pipeline
def train_and_test(model):#, train_data, train_label, test_data):
    model.fit(X_train, y_train)
    prediction = model.predict(test_data)
    accuracy_train = round(model.score(X_train_scaled, y_train) * 100, 2)
    accuracy_test = round(model.score(X_test_scaled, y_test) * 100, 2)

    #accuracy_train = round(model.score(X_resampled, y_resampled) * 100, 2)
    #accuracy_test = round(model.score(X_test_scaled, y_test) * 100, 2)
    print("Accuracy train : ", accuracy_train, "%")
    print("Accuracy test : ", accuracy_test, "%")
    return prediction



# Logistic Regression
log_pred = train_and_test(LogisticRegression())
# SVM
svm_pred = train_and_test(SVC())
#kNN
knn_pred_4 = train_and_test(KNeighborsClassifier(n_neighbors = 4))
# Random Forest
rf_pred = train_and_test(RandomForestClassifier(n_estimators=200))
# Navie Bayes
nb_pred = train_and_test(GaussianNB())


submission = pd.DataFrame({
    "edge_id": test["edge_id"],
    "addr": test["addr"],
    "light": test["light"],
    "cctv": test["cctv"],
    "police": test["police"],
    "convenience": test["convenience"],
    "road_type": test["road_type"],
    "safety_rate": log_pred
})

submission.to_csv('ai_data_tb2.csv', index=False)
