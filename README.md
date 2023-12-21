# MozArt

In this project, we have developed a model designed to detect images and identify the specific painting that a user is showcasing. The model incorporates a transfer learning approach, leveraging the InceptionV3 model. We enhance our accuracy through fine-tuning method.
And than we train our model with 100 epoch and 10 epoch for fine tuning
After that we got result like this :

![result](https://github.com/Altaair07/MozArt/assets/108602382/c3dbff80-3ec9-44a7-90a3-c381b6a2d388)


![result 2](https://github.com/Altaair07/MozArt/assets/108671086/ac974b53-5b62-4e2a-8afd-584e25604422)
![result3](https://github.com/Altaair07/MozArt/assets/108671086/583f98d1-5caf-499c-ae1e-0aaa2055108a)

### Function Dependencies

| Library      | Version |
| ------------ | ------- |
| Tensorflow   | 2.15.0  |
| Keras        | 2.15.0  |
| Matplotlib   | 3.7.1   |
| NumPy        | 1.23.5  |
| Pandas       | 3.7.1   |
| Scikit-learn | 1.2.2   |

### Metadata Writer

| Library        | Version |
| -------------- | ------- |
| Tensorflow     | 2.8.0   |
| Tflite_support | 0.4.4   |

## CNN

<p align="justify"> A basic model is built using Convolutional Neural Networks (CNN) as a foundation. CNN is used to recognize complex visual patterns in images, helping in the classification and recognition of objects in images at the Basoeki Abdullah Museum.</p>

## Transfer Learning InceptionV3

<p align="justify">
The implementation of transfer learning is carried out using InceptionV3, a well-known model architecture in image processing. Inception is used to utilize knowledge that has been previously obtained from pre-trained models and perform fine-tuning to improve model performance on painting datasets.
</p>

## Training, Fine-Tune and Training configurations

<p align="justify">
The model achieved 95% accuracy with an exceptional accuracy validation rate. The model was then implemented in TensorFlow format and converted to a tflite model for ease of use on lighter platforms such as mobile devices. In addition, the model is quantified to minimise its size, while important metadata is embedded in the model for additional information.
</p>
