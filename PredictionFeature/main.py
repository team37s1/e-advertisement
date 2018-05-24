from pandas import Series
from matplotlib import pyplot
from statsmodels.tsa.ar_model import AR
from statsmodels.tsa.ar_model import ARResults
from sklearn.metrics import mean_squared_error
import numpy


##################### PART1:READ AND TEST #####################


def difference(dataset):
	diff = list()
	for i in range(1, len(dataset)):
		value = dataset[i] - dataset[i - 1]
		diff.append(value)
	return numpy.array(diff)


def predict(coef, history):
	yhat = coef[0]
	for i in range(1, len(coef)):
		yhat += coef[i] * history[-i]
	return yhat

series = Series.from_csv('leveldata.csv', header=0)


X = difference(series.values)
size = int(len(X) * 0.66)
train, test = X[0:size], X[size:]


model = AR(train)
model_fit = model.fit(maxlag=6, disp=False)
window = model_fit.k_ar
coef = model_fit.params


history = [train[i] for i in range(len(train))]
predictions = list()
for t in range(len(test)):
	yhat = predict(coef, history)
	obs = test[t]
	predictions.append(yhat)
	history.append(obs)
error = mean_squared_error(test, predictions)
print('Test MSE: %.3f' % error)


pyplot.plot(test)
pyplot.plot(predictions, color='red')
pyplot.show()


##################### PART2:SAVE #####################


series = Series.from_csv('leveldata.csv', header=0)
X = difference(series.values)


model = AR(X)
model_fit = model.fit(maxlag=6, disp=False)


model_fit.save('ar_model.pkl')


numpy.save('ar_data.npy', X)


numpy.save('ar_obs.npy', [series.values[-1]])


##################### PART3:LOAD AND PREDICT #####################


model = ARResults.load('ar_model.pkl')
data = numpy.load('ar_data.npy')
last_ob = numpy.load('ar_obs.npy')


predictions = model.predict(start=len(data), end=len(data))


yhat = predictions[0] + last_ob[0]
print('Prediction: %f' % yhat)