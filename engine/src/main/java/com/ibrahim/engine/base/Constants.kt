package com.ibrahim.engine.base

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val TAG = "log*** emitter"
const val MIDDLE_MAN_PACKAGE_NAME = "com.ibrahim.middleman"
const val EMITTER_PACKAGE_NAME = "com.ibrahim.emitter"
const val RECEIVER_PACKAGE_NAME = "com.ibrahim.receiver"
const val RECEIVER_MAIN_ACTIVITY_PACKAGE_NAME = "$RECEIVER_PACKAGE_NAME.ReceiverMainActivity"
const val EMITTER_MAIN_ACTIVITY_PACKAGE_NAME = "$EMITTER_PACKAGE_NAME.users.UsersListActivity"

const val MIDDLE_MAN_RECEIVER_PACKAGE_NAME = "$MIDDLE_MAN_PACKAGE_NAME.MiddleManProdCastReceiver"
const val MIDDLE_MAN_RECEIVER_NAME = "$MIDDLE_MAN_PACKAGE_NAME.ACTION_SEND_USER"
const val EMITTER_RECEIVER_NAME = "$EMITTER_PACKAGE_NAME.ACTION_USER_ACTION_RESPONSE"
const val EMITTER_RECEIVER_package_NAME = "$EMITTER_PACKAGE_NAME.receiver.EmitterProdCastReceiver"
const val EXTRA_USER = "EXTRA_USER"
const val EXTRA_RESPONSE = "EXTRA_RESPONSE"