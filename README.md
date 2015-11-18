javautils
=========

javautils is a collection of helper classes for common tasks. It is made for
Android but has explicitly no Android dependency. So the classes can be used
for every other Java project but the APIs might be similar to the ones used on
Android.

## Contents

**Sorting**

Those sorting algorithms can be used when Java's TimSort is too intelligent for
your application, e.g. when your data changes during a `Collection.sort()` run.

All sorting classes make use of custom
[Comparator](http://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html)s.

 * QuickSort
 * InsertionSort

**Assertions**

Since the `assert` keyword is not reliable on Android, this is a drop-in replacement
for assertions that are executed in both debug and release builds.

 * RuntimeAssertion

**Encoding**

A base64 encoder/decoder that is as strict as possible, i.e. throws an exception
when an invalid character is found during decoding instead of ignoring it
(what Apache Commons Codec and Android's Base64 do).

 * StrictBase64

## Development

 * Test driven development
 * CI planned
 * Code is the documentation
 * APIs change without warning until someone tells me he is using this
 * Contributions welcome

## Requirements

 * Java 7 (code SHOULD run on Java 8 but MUST run with Java 7)

## License

```
Copyright 2014–2015 Simon Warta (Kullo GmbH)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
