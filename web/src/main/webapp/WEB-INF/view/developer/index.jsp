<html>
<body>
	<h1>YunOS Web</h1>

	<h2>You are: ${username }</h2>

	<h2>device operation</h2>

	<ul>

		<li><a
			href="../api/1.0/devices/cb170afb-087f-11e4-b721-08002785c3ec/0/transmit?appId=9db35bb6-10d6-11e4-b721-08002785c3ec&type=Nec&code=123&bits=24&repeat=1">IR
				Transmitter.transmit</a></li>
		<li><a
			href="../api/1.0/devices/cb170afb-087f-11e4-b721-08002785c3ec/1/transmit?appId=9db35bb6-10d6-11e4-b721-08002785c3ec&frequency=433&pulseLength=166&code=123&bits=24">test
				RF</a></li>
	</ul>

	<h2>RESTful RPC</h2>
	<ul>

		<li><a href="../api/1.0/devices?userId=jackding">list device
				of user</a></li>

		<li><a
			href="../api/1.0/devices/cb170afb-087f-11e4-b721-08002785c3ec">list
				device by id</a></li>

		<li><a href="../api/1.0/deviceClasses?locale=zh_CN">list
				device classes</a></li>

		<li><a href="../api/1.0/vendors?locale=zh_CN">list vendors</a></li>

		<li><a
			href="../api/1.0/vendors/c20c8c53-2485-11e4-9fa1-08002785c3ec/models?locale=zh_CN">list
				models</a></li>

		<li><a
			href="../api/1.0/drivers/b04a226f-2829-461d-8156-146c095e8794/configrationItems?locale=zh_CN">list
				driver configuration definitions</a></li>

		<li><a
			href="../api/1.0/drivers/?modelId=3a2725d7-087e-11e4-b721-08002785c3ec">list
				available drivers for model</a></li>

		<li><a
			href="../api/1.0/devices/cb170afb-087f-11e4-b721-08002785c3ec/configuration">get
				device configuration</a></li>
		<li><a
			href="../api/1.0/functionalDevices/by-device?deviceId=cb170afb-087f-11e4-b721-08002785c3ec&locale=zh_CN">get
				functional devices by device</a></li>
		<li><a
			href="../api/1.0/functionalDevices/by-user?userId=jackding&className=com.driverstack.yunos.deviceApi.transmitter.RfTransmitter&locale=zh_CN">query
				functional devices by user, organization, and artifact</a></li>

	</ul>

	<h2>driver and API upload</h2>
	<ul>
		<li><a href="../functionalDevice/">functional device CRUD</a></li>
		<li><a href="../driver/">driver CRUD</a></li>

	</ul>
</body>
</html>