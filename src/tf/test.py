import tensorflow as tf
import numpy as np
#r = tf.Variable(tf.random.uniform(
#    shape= (10,3), minval=-1, maxval=1, 
#    dtype=tf.dtypes.float32, seed=None
#    ))

#x = tf.compat.v1.get_variable('x', dtype = tf.float32, shape = [10, 3])
#y = tf.compat.v1.get_variable('y', dtype = tf.float32, shape = [10, 1])
#x = tf.compat.v1.assign(x, r);
#y = x[:,0] * 2;

nums = np.linspace(0, 2, 30);
#x = nums.reshape(10, 3);
#x = np.random.uniform(-1, 1, size= (10, 3));

#y = x[:,0] * 2;


x = np.array([1.0, 2.0, 3.0, 4.0, 5.0, 6.0], dtype=float)
y = np.array([1.0, 1.5, 2.0, 2.5, 3.0, 3.5], dtype=float)


x = x.reshape(6, 1)
y = y.reshape(6, 1)

print(x);
print(y);
# There are n columns in the feature matrix 
# after One Hot Encoding. 
X = tf.placeholder(tf.float32, shape = [None, 1]) 
  
# Since this is a binary classification problem, 
# Y can take only 2 values. 
Y = tf.placeholder(tf.float32, shape = [None, 1]) 
 
num_hidden_layers = 50
# Trainable Variable Weights 
W1 = tf.Variable(tf.random.normal(shape = [x.shape[1], num_hidden_layers])) 

# Trainable Variable Bias 
b1 = tf.Variable(tf.random.normal(shape = [num_hidden_layers])) 

# Trainable Variable Weights 
W2 = tf.Variable(tf.random.normal(shape = [num_hidden_layers, 1])) 
  
# Trainable Variable Bias 
b2 = tf.Variable(tf.random.normal(shape = [1])) 


hidden_out = tf.nn.relu(tf.add(tf.matmul(X, W1), b1))
#Y_predict = tf.nn.sigmoid(tf.add(tf.matmul(X, W1), b1))

Y_predict = tf.add(tf.matmul(hidden_out, W2), b2)


loss = tf.reduce_mean(tf.square(Y_predict - Y))

## Variable learning rate
start_learning_rate = 0.1
learning_rate = tf.train.exponential_decay(start_learning_rate, 0, 5, 0.85, staircase=True)
## Adam optimzer for finding the right weight
optimizer = tf.train.AdamOptimizer(learning_rate).minimize(loss,
        var_list=[W1, b1, W2, b2])
init = tf.global_variables_initializer();

with tf.Session() as sess:
    tf.global_variables_initializer()
    sess.run(init);
    for epoch in range(500):

        # Running the Optimizer
        feed_dict = {X : x, Y : y}
        sess.run(optimizer, feed_dict )

        # Calculating cost on current Epoch
        #c = sess.run(loss, feed_dict )
    
        print(epoch, " Loss: ", loss.eval(feed_dict));
print('Predicting')
y_pred= sess.run(Y_predict, {X : x})
print(y_pred);

        # Calculating accuracy on current Epoch
        #correct_prediction = tf.equal(tf.argmax(Y_predict, 1),
        #                                  tf.argmax(Y, 1))
        #accuracy = tf.reduce_mean(tf.cast(correct_prediction,
        #                                         tf.float32))

        # Storing Cost and Accuracy to the history
        #cost_history.append(sum(sum(c)))
        #accuracy_history.append(accuracy.eval({X : x, Y : y}) * 100)

        # Displaying result on current Epoch
        #if epoch % 100 == 0 and epoch != 0:
        #    print("Epoch " + str(epoch) + " Cost: "
        #                    + str(cost_history[-1]))

    #W1 = sess.run(W1) # Optimized Weight
    #b1 = sess.run(b1)   # Optimized Bias

    #W1 = sess.run(W1) # Optimized Weight
    #b1 = sess.run(b1)   # Optimized Bias
    # Final Accuracy
    #correct_prediction = tf.equal(tf.argmax(Y_hat, 1),
    #                                  tf.argmax(Y, 1))
    #accuracy = tf.reduce_mean(tf.cast(correct_prediction,
    #                                         tf.float32))
    #print("\nAccuracy:", accuracy_history[-1], "%")
#    x_value = sess.run(x);
#    y_value = sess.run(y);
#    print(x_value);
#    print(y_value);
#    print(sess.run(r[0]));

