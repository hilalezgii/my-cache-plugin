import Foundation
import Capacitor

@objc(ExamplePlugin)
public class ExamplePlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "ExamplePlugin"
    public let jsName = "ExamplePlugin"
    private let implementation = Example()

    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "set", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "get", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "remove", returnType: CAPPluginReturnPromise)
    ]

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value", "")
        call.resolve(["value": implementation.echo(value)])
    }

    @objc func get(_ call: CAPPluginCall) {
        let key = call.getString("key", "")
        let value = implementation.get(key: key) ?? ""
        call.resolve(["value": value])
    }

    @objc func set(_ call: CAPPluginCall) {
        let key = call.getString("key", "")
        let value = call.getString("value", "")
        implementation.set(key: key, value: value)
        call.resolve()
    }

    @objc func remove(_ call: CAPPluginCall) {
        let key = call.getString("key", "")
        implementation.remove(key: key)
        call.resolve()
    }
}
