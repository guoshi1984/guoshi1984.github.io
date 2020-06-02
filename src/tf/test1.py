import tensorflow.compat.v1 as tf
import numpy as np
from tensorflow import keras
from tensorflow.compat.v1.keras import backend as k

## Model 1: y = x
model1 = tf.keras.Sequential([keras.layers.Dense(units=50, input_shape=[1], activation = 'relu', name = 'layer1')])
model1.add(keras.layers.Dense(units=10, input_shape=[50], activation = 'relu', name = 'layer2'))
model1.add(keras.layers.Dense(units=1, input_shape=[10], name = 'layer3'))
model1.compile(optimizer='Adam', loss='mean_squared_error')
model1.summary();
xs = np.array([0.1, 0.2, 0.3, 0.4, 0.5, 0.6], dtype=float)
#xs = np.array([[1.0, 1.0], [2.0, 1.0], [3.0, 1.0], [4.0, 1.0], [5.0, 1], [6.0, 1]], dtype=float)
ys = np.array([[0.1], [0.2], [0.3], [0.4], [0.5], [0.6]], dtype=float)

xr = np.random.uniform(-1, 1, size= (1000));
yr = xr;

model1.fit(xr, yr, epochs=500)

test_x_1 = np.array([[0.70]]);
print("Model 1: x: ", test_x_1,"predict of y: ", model1.predict(test_x_1))


session = k.get_session()
#session.run(tf.global_variables_initializer())
y_grad_evaluated = session.run(tf.gradients(model1.output, model1.input), feed_dict={model1.input: xr.reshape(100, 1)})
print("dy/dx: ", np.mean(y_grad_evaluated))
print("===================================")




## Model 2: y = 2*x
model2 = tf.keras.Sequential([keras.layers.Dense(units=50, input_shape=[1], activation = 'relu', name = 'layer1')])
model2.add(keras.layers.Dense(units=10, input_shape=[50], activation = 'relu', name = 'layer2'))
model2.add(keras.layers.Dense(units=1, input_shape=[10], name = 'layer3'))
model2.compile(optimizer='Adam', loss='mean_squared_error')
model2.summary();
#xs = np.array([[1.0, 1.0], [2.0, 1.0], [3.0, 1.0], [4.0, 1.0], [5.0, 1], [6.0, 1]], dtype=float)
yr = xr*2.0;
model2.fit(xr, yr, epochs=500)

#test_x_1 = np.array([[7.0, 1],]);
test_x_1 = np.array([[0.7]]);
print(test_x_1.shape);
print("Model 2: x: ", test_x_1,"predict of y: ", model2.predict(test_x_1))


session = k.get_session()
#session.run(tf.global_variables_initializer())
y_grad_evaluated = session.run(tf.gradients(model2.output, model2.input), feed_dict={model2.input: xr.reshape(100,1)})
print("dy/dx: ", np.mean(y_grad_evaluated))

print("===================================")


## Model 3: y = 2*x + 1
model3 = tf.keras.Sequential([keras.layers.Dense(units=50, input_shape=[1], activation = 'relu', name = 'layer1')])
model3.add(keras.layers.Dense(units=10, input_shape=[50], activation = 'relu', name = 'layer2'))
model3.add(keras.layers.Dense(units=1, input_shape=[10], name = 'layer3'))
model3.compile(optimizer='Adam', loss='mean_squared_error')
model3.summary();
xs = np.array([1.0, 2.0, 3.0, 4.0, 5.0, 6.0], dtype=float)
#xs = np.array([[1.0, 1.0], [2.0, 1.0], [3.0, 1.0], [4.0, 1.0], [5.0, 1], [6.0, 1]], dtype=float)
ys = np.array([[3.0], [5.0], [7.0], [9.0], [11.0], [13.0]], dtype=float)
yr = xr*2.0 + 1
model3.fit(xr, yr, epochs=500)

#test_x_1 = np.array([[7.0, 1],]);
test_x_1 = np.array([[0.7]]);
print(test_x_1.shape);
print("x: ", test_x_1,"predict of y: ", model3.predict(test_x_1))


session = k.get_session()
#session.run(tf.global_variables_initializer())
y_grad_evaluated = session.run(tf.gradients(model3.output, model3.input), feed_dict={model3.input: xr.reshape(100, 1)})
print("dy/dx: ", np.mean(y_grad_evaluated))
